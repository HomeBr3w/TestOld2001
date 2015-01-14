#ifndef CAMERA_H
#define CAMERA_H

#endif // CAMERA_H

#include <QMultimedia>
#include <QCamera>
#include <QCameraViewfinder>
#include <QCameraImageCapture>

class Camera
{

private:
    QCamera* camera;
    QWidget* cameraWidget;
    QCameraViewfinder* cameraViewFinder;
    void initCamera();


public:
    QImage takeSnapshot();
    Camera(QWidget* widget);
    ~Camera();

};
