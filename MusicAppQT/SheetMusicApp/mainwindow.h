#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QtWidgets>
#include "camera.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:

    void on_sendPictureButton_clicked();

    void on_takePictureButton_clicked();

private:
    Ui::MainWindow *ui;
    Camera* camera;
    void cameraInit();
};

#endif // MAINWINDOW_H
