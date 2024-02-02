document.getElementById("Projectform").addEventListener("submit", function (event) {
           event.preventDefault();

           var formData = {
               rsi: document.getElementById("rsi").checked,
               ibm: document.getElementById("ibm").checked,
               mt: document.getElementById("mt").checked,
               ac: document.getElementById("ac").checked,
               aapl: document.getElementById("aapl").checked,
               amzn: document.getElementById("amzn").checked,
               tsla: document.getElementById("tsla").checked,
               msft: document.getElementById("msft").checked,
               uber: document.getElementById("uber").checked,
               dash: document.getElementById("dash").checked,
               wmt: document.getElementById("wmt").checked,
               nke: document.getElementById("nke").checked,
               nflx: document.getElementById("nflx").checked,
               bx: document.getElementById("bx").checked,
               t: document.getElementById("t").checked,
               amd: document.getElementById("amd").checked,
               sbux: document.getElementById("sbux").checked,
               dis: document.getElementById("dis").checked,
               f: document.getElementById("f").checked,
               meta: document.getElementById("meta").checked,
               ge: document.getElementById("ge").checked,
               lmt: document.getElementById("lmt").checked,
               nvda: document.getElementById("nvda").checked
           };

           fetch('/getAnalysis', {
               method: 'POST',
               headers: {
                   'Content-Type': 'application/json',
               },
               body: JSON.stringify(formData),
           })
           .then(response => response.json())
           .then(dataj => {
           if(dataj.rsi){displayData(dataj.rsi);}
           if(dataj.ac){displayData3(dataj.ac);}
           if(dataj.mt){displayData2(dataj.mt);}
           })
           .catch((error) => {
               console.error('Error:', error);
           });
       });

        function displayData(dataArray) {
            var container = document.getElementById("rsi-container");
            dataArray.forEach(function (item) {
                var div = document.createElement('div');
                div.innerHTML = 'Symbol: ' + item.symbol + '<br>' +
                    'Company: ' + item.company + '<br>' +
                    'Current Price: $' + item.currentPrice + '<br>' +
                    'Price Change: ' + item.priceChange + '<br>' +
                    'Previous Close: $' + item.previousClose + '<br>' +
                    'RSI: ' + item.RSI + '<br><br>';
                container.appendChild(div);
            });
            container.style.display = 'block';
        }
        function displayData2(dataArray) {
                    var container = document.getElementById("mt-container");
                    dataArray.forEach(function (item) {
                        var div = document.createElement('div');
                        div.innerHTML = 'Symbol: ' + item.symbol + '<br>' +
                            'NewsSummary: $' + item.newsSummary + '<br>' +
                            'MarketTrend: ' + item.marketTrend + '<br><br>';
                        container.appendChild(div);
                    });
                    container.style.display = 'block';
                }
                function displayData3(dataArray) {
                            var container = document.getElementById("ac-container");
                            dataArray.forEach(function (item) {
                                var div = document.createElement('div');
                                div.innerHTML = 'Symbol: ' + item.symbol + '<br>' +
                                    'Company: ' + item.companyName + '<br>' +
                                    'TargetsGenerated 1:' + item.targetsGenerated + '<br>' +
                                    'Accuracy 1:' + item.accuracy + '<br>' +
                                    'TargetsGenerated 2: ' + item.targetsGenerated2 + '<br>'+
                                    'Targets Hit 1: ' + item.targetsHit2 + '<br>'+
                                    'Accuracy 2: ' + item.accuracy2 + '<br><br>';
                                container.appendChild(div);
                            });
                            container.style.display = 'block';
                        }
