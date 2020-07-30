function gotoTop(acceleration,time) {
    acceleration = acceleration || 0.1;
    time = time || 10;
    let x1 = 0;
    let y1 = 0;
    let x2 = 0;
    let y2 = 0;
    let x3 = 0;
    let y3 = 0;
    if (document.documentElement) {
        x1 = document.documentElement.scrollLeft || 0;
        y1 = document.documentElement.scrollTop || 0;
    }
    if (document.body) {
        x2 = document.body.scrollLeft || 0;
        y2 = document.body.scrollTop || 0;
    }
    x3 = window.scrollX || 0;
    y3 = window.scrollY || 0;

    // 滚动条到页面顶部的水平距离
    const x = Math.max(x1, Math.max(x2, x3));
    // 滚动条到页面顶部的垂直距离
    const y = Math.max(y1, Math.max(y2, y3));

    // 滚动距离 = 目前距离 / 速度, 因为距离原来越小, 速度是大于 1 的数, 所以滚动距离会越来越小
    const speeding = 1 + acceleration;
    window.scrollTo(Math.floor(x / speeding), Math.floor(y / speeding));

    // 如果距离不为零, 继续调用函数
    if(x > 0 || y > 0) {
        const run = "gotoTop(" + acceleration + ", " + time + ")";
        window.setTimeout(run, time);
    }
}