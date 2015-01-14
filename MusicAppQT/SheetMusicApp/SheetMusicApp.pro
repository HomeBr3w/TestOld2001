#-------------------------------------------------
#
# Project created by QtCreator 2015-01-14T13:16:45
#
#-------------------------------------------------

QT += core gui
QT += multimedia multimediawidgets

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = SheetMusicApp
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    camera.cpp \
    audio.cpp

HEADERS  += mainwindow.h \
    camera.h \
    audio.h

FORMS    += mainwindow.ui

CONFIG += mobility
MOBILITY = 

