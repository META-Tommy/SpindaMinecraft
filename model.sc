__command() -> build();
poly(xx,yy,zz,xNum,yNum,zNum,xOff,yOff,zOff,scaleo,size,color) -> (
    scale = 5.71*scaleo;
    c = str('{"text":"⬛","color":"#%x"}',color);
    xAxis = -yNum;
    yAxis = xNum;
    zAxis = 0;
    l = sqrt(xAxis^2+yAxis^2+zAxis^2);
    if(l==0,
    xAxis=0;
    yAxis=0;
    zAxis=1;
    ,xAxis=xAxis/l;
    yAxis=yAxis/l;
    zAxis=zAxis/l;);
    
    dangle = acos(zNum);
    angle = dangle*pi/180;
    sangle = sin(dangle);
    xpt = -scale/80;
    ypt = -scale/6.666;
    zpt = 0;
    xcross = yAxis*zpt-zAxis*ypt;
    ycross = zAxis*xpt-xAxis*zpt;
    zcross = xAxis*ypt-yAxis*xpt;
    dot = xAxis*xpt+yAxis*ypt+zAxis*zpt;
    xt = zNum*xpt+sangle*xcross+(1-zNum)*(dot)*xAxis+xOff*size;
    yt = zNum*ypt+sangle*ycross+(1-zNum)*(dot)*yAxis+yOff*size;
    zt = zNum*zpt+sangle*zcross+(1-zNum)*(dot)*zAxis+zOff*size;
    run('summon minecraft:text_display '+(fix(xx))+' '+(fix(yy))+' '+(fix(zz))+' {text:\''+c+'\',interpolation_start:-1,background:0,interpolation_duration:0,transformation:{left_rotation:{axis:['+xAxis+'f,'+yAxis+'f,'+zAxis+'f],angle:'+angle+'f},right_rotation:[0f,0f,0f,1f],translation:['+xt+'f,'+yt+'f,'+zt+'f],scale:['+scale+'f,'+scale+'f,'+scale+'f]}}');
    
);
fix(n) -> (
    if(n%1==0,return(n+'.0'));
    return(n);
);
render(xx, yy, zz, scale, block) -> (
    run('kill @e[type=minecraft:text_display]');
    read = synchronize(read_file('model_info', 'shared_text'));
    current = 0;
    size = number(read:0);
    loop(size,
        if(current%500 == 0,game_tick());
        current+=1;

        nx=number(read:(_*3+1));
        ny=number(read:(_*3+2));
        nz=number(read:(_*3+3));
        ox=number(read:(_*3+1+size*3));
        oy=number(read:(_*3+2+size*3));
        oz=number(read:(_*3+3+size*3));
        ps=number(read:(_+1+size*6));
        shade = 2;
        rint = (ny+nx+nz)/4+2/3;
        gint = rint;
        bint = rint;
        cio=number(read:(_+1+size*7));
        rio=floor(cio/65536)%256;
        gio=floor(cio/256)%256;
        bio=cio%256;
        rclamp=clamp(rio*rint,0,255);
        gclamp=clamp(gio*gint,0,255);
        bclamp=clamp(bio*bint,0,255);
        color=rclamp*65536+gclamp*256+bclamp;
        poly(number(xx),number(yy),number(zz),
            nx,ny,nz,
            ox,oy,oz,
            number(scale)*number(ps),block,color);
    );
);
clamp(n,min,max)->(
    if(n>max,return(max));
    if(n<min,return(min));
    return(floor(n));
);