#include <graphics.h>
#include <iostream>
#include <math.h>
using namespace std;

int main(){
    int gd=DETECT, gm;
    initgraph(&gd,&gm,(char*)"");

    int x1=100,y1=100,x2=200,y2=100,x3=150,y3=200;

    int ch, tx,ty,sx,sy,shx,shy;
    float angle;

    cout<<"\n1.Translation\n2.Scaling\n3.Rotation\n4.Shearing\nEnter choice:";
    cin>>ch;

    cleardevice();
    setcolor(WHITE);
    line(x1,y1,x2,y2);
    line(x2,y2,x3,y3);
    line(x3,y3,x1,y1);
    outtext(10,10,(char*)"Original Object");

    switch(ch){
        case 1:
            cout<<"Enter tx, ty:";
            cin>>tx>>ty;
            x1+=tx; y1+=ty;
            x2+=tx; y2+=ty;
            x3+=tx; y3+=ty;
            break;

        case 2:
            cout<<"Enter sx, sy:";
            cin>>sx>>sy;
            x1*=sx; y1*=sy;
            x2*=sx; y2*=sy;
            x3*=sx; y3*=sy;
            break;

        case 3:
            cout<<"Enter angle in degrees:";
            cin>>angle;
            angle = angle*3.14/180;
            x1 = x1*cos(angle)-y1*sin(angle);
            y1 = x1*sin(angle)+y1*cos(angle);
            x2 = x2*cos(angle)-y2*sin(angle);
            y2 = x2*sin(angle)+y2*cos(angle);
            x3 = x3*cos(angle)-y3*sin(angle);
            y3 = x3*sin(angle)+y3*cos(angle);
            break;

        case 4:
            cout<<"Enter shx, shy:";
            cin>>shx>>shy;
            x2 = x2 + shx*y2;
            x3 = x3 + shx*y3;
            y2 = y2 + shy*x2;
            y3 = y3 + shy*x3;
            break;

        default: cout<<"Invalid";
    }

    setcolor(RED);
    line(x1,y1,x2,y2);
    line(x2,y2,x3,y3);
    line(x3,y3,x1,y1);
    outtext(10,20,(char*)"Transformed Object");

    getch();
    closegraph();
    return 0;
}