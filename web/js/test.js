cities={};
cities['China']=["Shanghai", "Beijing","Chongqing","Hongkong"];
cities['Japan']=["Osaka","Kyoto","Tokyo","Kamakura"];
cities['Italy']=["Florence","Rome","Milan","Venice"];
cities['America']=["New York","San Francisco","Los Angles","Washington"];

function set_city(province, city)
{
    var pv, cv;
    var i, ii;

    pv=province.value;
    cv=city.value;

    city.length=1;

    if(pv=='0') return;
    if(typeof(cities[pv])=='undefined') return;

    for(i=0; i<cities[pv].length; i++)
    {
        ii = i+1;
        city.options[ii] = new Option();
        city.options[ii].text = cities[pv][i];
        city.options[ii].value = cities[pv][i];
        //document.write(city.options[ii].value);
    }
}