$(document).ready(function () {
    $("#btnForm").click(function () {

            var num = $("#cityQuantity").val();
            var div = document.getElementById('share');
            jQuery('#share').html('');


            var form = document.createElement('form');
            /*-----form.setAttribute('action', 'sharer.php');-----*/
            form.setAttribute('method', 'POST');
            form.setAttribute('id', 'mainForm');
            form.setAttribute('name', 'mainForm');
            form.setAttribute('class', 'form-inline text-center');
            /*-----------*/

            for (var i = 1; i <= num; i++) {
                input = jQuery('<br><input type="text" id = "city1" name="text1" size="40">');
                jQuery('#pet').append(input);


                var mainDiv = document.createElement('div');
                mainDiv.setAttribute('class', 'form-group');
                mainDiv.setAttribute('id', 'mainDiv' + i);
                mainDiv.setAttribute('style', 'padding-bottom: 5px');

                var div1 = document.createElement('div');
                div1.setAttribute('class', 'form-group test');
                /*-----------*/

                var label1 = document.createElement('label');
                label1.setAttribute('class', 'sr-only');
                label1.setAttribute('for', 'cityName' + i);
                label1.setAttribute('value', 'Название города');

                var input1 = document.createElement('input');
                input1.setAttribute('type', 'text');
                input1.setAttribute('placeholder', 'Название города');
                input1.setAttribute('name', 'cityName' + i);
                input1.setAttribute('id', 'cityName' + i);
                input1.setAttribute('class', 'form-control');


                div1.appendChild(label1);
                div1.appendChild(input1);
                div1.setAttribute('style', 'padding-right: 5px');


                var div2 = document.createElement('div');
                div2.setAttribute('class', 'form-group test');
                div2.setAttribute('style', 'padding-right: 5px');


                var label2 = document.createElement('label');
                label2.setAttribute('class', 'sr-only');
                label2.setAttribute('for', 'cityName' + i);
                label2.setAttribute('value', 'Название города');

                var input2 = document.createElement('input');
                input2.setAttribute('type', 'text');
                input2.setAttribute('placeholder', 'Количество ночей');
                input2.setAttribute('name', 'nightsQuantity' + i);
                input2.setAttribute('id', 'nightsQuantity' + i);
                input2.setAttribute('class', 'form-control');

                div2.appendChild(label2);
                div2.appendChild(input2);


                var div3 = document.createElement('div');
                div3.setAttribute('class', 'checkbox');
                div3.setAttribute('style', 'padding-right: 5px');


                var label3 = document.createElement('label');
                label3.innerHTML = '<input type="checkbox" name="startChb' + i + '" id="startChb' + i + '"> Начать с этого города';

                div3.appendChild(label3);

                var div4 = document.createElement('div');
                div4.setAttribute('class', 'checkbox');
                div4.setAttribute('style', 'padding-right: 5px');


                var label4 = document.createElement('label');
                label4.innerHTML = '<input type="checkbox" name="endChb' + i + '" id="endChb' + i + '"> Закончить этим городом';

                div4.appendChild(label4);

                var submit = document.createElement('input');
                submit.setAttribute('type', 'submit');
                submit.setAttribute("value", "Составить маршрут");
                submit.setAttribute('class', 'btn btn-primary');

                var br = document.createElement('br');

                mainDiv.appendChild(div1);
                mainDiv.appendChild(div2);
                mainDiv.appendChild(div3);
                mainDiv.appendChild(div4);
                form.appendChild(mainDiv);
                form.appendChild(br);
            }

            var div5 = document.createElement('div');
            div5.setAttribute('class', 'form-group');
            div5.setAttribute('style', 'padding-right: 5px; padding-bottom: 5px');


            var label5 = document.createElement('label');
            label5.setAttribute('class', 'sr-only');
            label5.setAttribute('for', 'startDt');
            label5.innerHTML = 'городом';

            var input5 = document.createElement('input');
            input5.setAttribute('type', 'text');
            input5.setAttribute('placeholder', 'Дата начала');
            input5.setAttribute('name', 'startDt');
            input5.setAttribute('id', 'startDt');
            input5.setAttribute('class', 'form-control');

            div5.appendChild(label5);
            div5.appendChild(input5);

            var div6 = document.createElement('div');
            div6.setAttribute('class', 'form-group');
            div6.setAttribute('style', 'padding-right: 5px; padding-bottom: 5px');


            var label6 = document.createElement('label');
            label6.setAttribute('class', 'sr-only');
            label6.setAttribute('for', 'endDt');

            var input6 = document.createElement('input');
            input6.setAttribute('type', 'text');
            input6.setAttribute('placeholder', 'Дата окончания');
            input6.setAttribute('name', 'endDt');
            input6.setAttribute('id', 'endDt');
            input6.setAttribute('class', 'form-control');

            div6.appendChild(label6);
            div6.appendChild(input6);


            var br = document.createElement('br');
            form.appendChild(div5)
            form.appendChild(div6)
            form.appendChild(br);
            form.appendChild(submit);

            div.appendChild(form);
            myFunction1();
            myFunction3();
            myFunction4();
        }
    );

});