#include "camera.h"

Camera::Camera(QWidget* widget)
{
    cameraWidget = widget;
    camera = new QCamera();
    cameraViewFinder = new QCameraViewfinder(cameraWidget);
    initCamera();
}

Camera::~Camera()
{
    delete camera;
    delete cameraViewFinder;
    delete cameraWidget;
}

void Camera::initCamera()
{
    cameraViewFinder->setSizePolicy(QSizePolicy::Maximum, QSizePolicy::Maximum);
    camera->setViewfinder(cameraViewFinder);
    //cameraWidget = &cameraViewFinder;
    cameraViewFinder->show();
    camera->start();
}



QImage Camera::takeSnapshot()
{
    camera->setCaptureMode(QCamera::CaptureStillImage);
    QCameraImageCapture* imageCapture = new QCameraImageCapture(camera);
    camera->searchAndLock();
    int imageId = imageCapture->capture(QString("capturedImage.jpg"));
    camera->unlock();
    const QImage capturedImg;
    imageCapture->imageCaptured(imageId, capturedImg);\
    return capturedImg.copy();
}

