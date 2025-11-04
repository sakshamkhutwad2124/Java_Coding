#include <graphics.h>
#include <iostream>
using namespace std;

int poly[10][2];    // polygon points
int n;              // number of vertices
int fillColor = RED;
int boundaryColor = WHITE;

void boundaryFill(int x,int y,int fillc,int bc){
    int c = getpixel(x,y);
    if(c!=fillc && c!=bc){
        putpixel(x,y,fillc);
        boundaryFill(x+1,y,fillc,bc);
        boundaryFill(x-1,y,fillc,bc);
        boundaryFill(x,y+1,fillc,bc);
        boundaryFill(x,y-1,fillc,bc);
    }
}

void floodFill(int x,int y,int oldc,int newc){
    int c = getpixel(x,y);
    if(c==oldc){
        putpixel(x,y,newc);
        floodFill(x+1,y,oldc,newc);
        floodFill(x-1,y,oldc,newc);
        floodFill(x,y+1,oldc,newc);
        floodFill(x,y-1,oldc,newc);
    }
}

void scanFill(){
    int ymin = 480, ymax = 0;

    for(int i=0;i<n;i++){
        if(poly[i][1] < ymin) ymin = poly[i][1];
        if(poly[i][1] > ymax) ymax = poly[i][1];
    }

    for(int y=ymin; y<=ymax; y++){
        int inter[20], k=0;
        for(int i=0;i<n;i++){
            int x1 = poly[i][0], y1 = poly[i][1];
            int x2 = poly[(i+1)%n][0], y2 = poly[(i+1)%n][1];
            if(y >= min(y1,y2) && y< max(y1,y2)){
                int x = x1 + (float)(y-y1)*(x2-x1)/(y2-y1);
                inter[k++] = x;
            }
        }
        sort(inter, inter+k);
        for(int i=0;i<k;i+=2){
            line(inter[i],y,inter[i+1],y);
        }
    }
}

int main(){
    int gd=DETECT, gm;
    initgraph(&gd,&gm,(char*)"");

    cout<<"Enter number of vertices: "; cin>>n;
    cout<<"Enter x y coordinates: \n";
    for(int i=0;i<n;i++){
        cin>>poly[i][0]>>poly[i][1];
    }

    // draw polygon outline
    for(int i=0;i<n;i++){
        line(poly[i][0],poly[i][1], poly[(i+1)%n][0], poly[(i+1)%n][1]);
    }

    int ch;
    cout<<"\nMENU\n1.Scan Fill\n2.Boundary/Seed Fill\n3.Flood Fill\nEnter choice:";
    cin>>ch;

    switch(ch){
        case 1: scanFill(); break;
        case 2: boundaryFill(poly[0][0]+2, poly[0][1]+2, fillColor,boundaryColor); break;
        case 3: floodFill(poly[0][0]+2, poly[0][1]+2, BLACK, fillColor); break;
        default: cout<<"Invalid";
    }

    getch();
    closegraph();
    return 0;
}