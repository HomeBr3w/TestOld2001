#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    cameraInit();
}

MainWindow::~MainWindow()
{
    delete ui, camera;
}

void MainWindow::cameraInit()
{
    camera = new Camera(ui->cameraWidget);
}


void MainWindow::on_sendPictureButton_clicked()
{
    //TODO: send to server.
}

void MainWindow::on_takePictureButton_clicked()
{
    camera->takeSnapshot();
}
