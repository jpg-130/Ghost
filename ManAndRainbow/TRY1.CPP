#include<conio.h>
#include<dos.h>
#include<stdlib.h>
#include<stdio.h>
#include<graphics.h>
#include<math.h>
int q,r,d;
void rain()
{
    for (int i = 0; i < 200; ++i)
    {
	int x=600,y=400;
	setcolor(WHITE);
	line(0,430,639,430);
	int rx=rand()%639;
	int ry=rand()%439;
	if(rx>=(x-40)&&rx<=(x+110))
	if(ry>=(y-50)&&ry<=479)
	    continue;
	if(i%2==0)
		setcolor(CYAN);
	else
		setcolor(BLACK);
	x=x%639;
	y=y%430;
	line(rx-10,ry+10,rx,ry);

	//line(rx-10,ry+10,rx,ry);
	//setcolor(CYAN);
    }
    //line(0,-1,700,-1);
   // delay(1);
}

void displayMan(int x,int y)

{
    setcolor(WHITE);
    line(0,430,639,430);
    circle(x,y,10);         //face
    line(x,y+10,x,y+30);        //neck
    line(x,y+30,x-20,y+40);    //left hand
    line(x,y+30,x+20,y+40);    //right hand
    line(x+20,y+40,x+30,y+30);
    line(x,y+30,x,y+70);        //body
    line(x+30,y+30,x+30,y-90);  //umbrella
    //setcolor(RED);
    pieslice(x+30,y-30,0,180,55);
    //setcolor(WHITE);
}

void displayMans(int x,int y)
{
	setcolor(WHITE);
	line(0,430,639,430);
    circle(x,y,10);         //face
    line(x,y+10,x,y+30);        //neck
    line(x,y+30,x-20,y+40);    //left hand
    line(x,y+30,x+20,y+40);    //right hand
    line(x+20,y+40,x+30,y+30);
    line(x-20,y+40,x-30,y+30);
    //line(x,y+30,x,y+70);   
    line(x,y+30,x,y+70);     //body
    line(x,y+70,x-d,y+90);
    line(x,y+70,x+d,y+90);	//leg
    //line(x+30,y+30,x+30,y-90);  //umbrella
    //pieslice(x+30,y-30,0,180,55);

}

 int color[21]={1,1,1,3,3,3,2,2,2,10,10,10,14,14,14,12,12,12,4,4,4};
 int m[4]={-500,-100,300,700};
 int n[4]={430,10,10,430};
 int p[4]={0,10,20,30};
 int c[4]={50,49,48,47};

 void inx(int x[4])
{
    int i;
    for(i=0;i<4;++i)
    {
    if(x[i]<(x[0]+x[4])/2)
    x[i]+=	2;
    else
    x[i]-=2;
    }
}

void iny(int n[4])
{
    n[1]+=2;
    n[2]+=2;
}


void bezier (int x[4], int y[4])
{
    int gd = DETECT, gm;
    int i=1;
    double t;

    initgraph (&gd, &gm, "..\\bgi");

    //rain();

    for (i=20; i>=0; i--)
    {
	setcolor(WHITE);
		line(0,430,639,430);
	    displayMans(q,r);
	    for (t = 0.0; t < 1.0; t += 0.00005)
	    {
		double xt = pow (1-t, 3) * x[0] + 3 * t * pow (1-t, 2) * x[1] +
		    3 * pow (t, 2) * (1-t) * x[2] + pow (t, 3) * x[3];

		double yt = pow (1-t, 3) * y[0] + 3 * t * pow (1-t, 2) * y[1] +
		    3 * pow (t, 2) * (1-t) * y[2] + pow (t, 3) * y[3];

		putpixel (xt, yt, color[i]);
		//delay(1);
	    }
	    inx(x);
	    iny(y);
	    rain();

    }
    //putpixel (x[i], y[i], YELLOW);

    //getch();
    closegraph();
    return;
}

void sun(int x[4],int y[4])
{
int gd = DETECT, gm;
    int i=1;
    double t;

    initgraph (&gd, &gm, "..\\bgi");

    //rain();
	    displayMans(q,r);
	    for (t = 0.0; t < 1.0; t += 0.00005)
	    {
		double xt = pow (1-t, 3) * x[0] + 3 * t * pow (1-t, 2) * x[1] +
		    3 * pow (t, 2) * (1-t) * x[2] + pow (t, 3) * x[3];

		double yt = pow (1-t, 3) * y[0] + 3 * t * pow (1-t, 2) * y[1] +
		    3 * pow (t, 2) * (1-t) * y[2] + pow (t, 3) * y[3];

		circle(xt,yt,20);
		
    }
    iny(x);
    inx(y);
    closegraph();
    return;
}

/*void main()
{
    bezier (x, y);
}
 */

void main()
{
    int gd=DETECT, gm,i,x=600,y=340,shouldMove=1;
    int rx,ry;
    initgraph(&gd,&gm,"C:\\tc\\bgi");

    d=0;
    while(x!=0)//!kbhit()&& x!=0)
    {
	cleardevice();
	displayMan(x,340);
	setcolor(WHITE);
	line(0,430,639,430);
	line(0,230,100,240);

//for(i=0;i<500;i++)
  //	{
	    /*rx=rand()%639;
	    ry=rand()%439;
	    if(rx>=(x-40)&&rx<=(x+110))
		if(ry>=(y-50)&&ry<=479)
		    continue;
	    line(rx-10,ry+10,rx,ry);*/
	rain();
    //	}

	//legs
	if(shouldMove)
	{
	    if(d<20)
		d+=4;
	    else
		shouldMove=0;
		setcolor(WHITE);
	    line(x,y+70,x-d,y+90);
	    line(x,y+70,x+d,y+90);
	}
	else
	{     if(d>0)
		d-=4;
	    else
		shouldMove=1;
		setcolor(WHITE);
	    line(x,y+70,x-d,y+90);
	    line(x,y+70,x+d,y+90);
       //	}
	delay(200);
	x=(x-20)%639;
    }

}
if(x==0){

	do
	{
	    cleardevice();
	    displayMan(x,340);
	setcolor(WHITE);
	    line(0,430,639,430);
		line(0,230,100,240);
	    rain();
	    if(shouldMove)
	    {
			if(d<20)
			d+=4;
			else
			shouldMove=0;
			setcolor(WHITE);
		    line(x,y+70,x-d,y+90);
		    line(x,y+70,x+d,y+90);
	    }
	    else
	    {   if(d>0)
				d-=4;
			else
				shouldMove=1;
				setcolor(WHITE);
				line(x,y+70,x-d,y+90);
				line(x,y+70,x+d,y+90);
			    delay(200);
			    x=(x+2)%639;
	    }

	} while(x<100);
}
//if(x>100)
//{
	rain();
	q=x;
	r=y;
	setcolor(WHITE);
    displayMans(q,r);
    setcolor(WHITE);
	line(x,y+70,x-d,y+90);
	line(x,y+70,x+d,y+90);
    setcolor(YELLOW);
    //ellipse(m[0],n[0]-20,0,90,100,50);
    sun(p,c);
    bezier(m,n);
//}

getch();

}