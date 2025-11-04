#include <graphics.h>
#include <iostream>
using namespace std;

int xmin=100, ymin=100, xmax=300, ymax=300;

int LEFT=1, RIGHT=2, BOTTOM=4, TOP=8;

int getCode(int x, int y){
    int code = 0;
    if (x < xmin) code |= LEFT;
    else if (x > xmax) code |= RIGHT;
    if (y < ymin) code |= BOTTOM;
    else if (y > ymax) code |= TOP;
    return code;
}

int main(){
    int gd=DETECT, gm;
    initgraph(&gd,&gm,(char*)"");

    // Draw clipping rectangle
    rectangle(xmin,ymin,xmax,ymax);

    // Input line endpoints
    int x1,y1,x2,y2;
    cout<<"Enter line endpoints x1 y1 x2 y2 : ";
    cin>>x1>>y1>>x2>>y2;

    // Draw original line
    line(x1,y1,x2,y2);

    int c1 = getCode(x1,y1);
    int c2 = getCode(x2,y2);

    int accept = 0;

    while(true){
        if(c1==0 && c2==0){
            accept = 1;
            break;
        }
        else if(c1 & c2){
            break;
        }
        else{
            int x,y;
            int code = c1?c1:c2;

            double m = (double)(y2-y1)/(x2-x1);

            if(code & TOP){
                x = x1 + (ymax - y1)/m;
                y = ymax;
            }
            else if(code & BOTTOM){
                x = x1 + (ymin - y1)/m;
                y = ymin;
            }
            else if(code & RIGHT){
                y = y1 + m*(xmax - x1);
                x = xmax;
            }
            else{
                y = y1 + m*(xmin - x1);
                x = xmin;
            }

            if(code == c1){
                x1=x; y1=y;
                c1 = getCode(x1,y1);
            }
            else{
                x2=x; y2=y;
                c2 = getCode(x2,y2);
            }
        }
    }

    cleardevice();

    rectangle(xmin,ymin,xmax,ymax);

    if(accept){
        setcolor(RED);
        line(x1,y1,x2,y2);
        outtext(50,50,(char*)"Line ACCEPTED");
    }
    else{
        outtext(50,50,(char*)"Line REJECTED");
    }

    getch();
    closegraph();
    return 0;
}