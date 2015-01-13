/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.4.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QGraphicsView>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QProgressBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QAction *actionLoad_image;
    QAction *actionScan_image;
    QAction *actionOpen_image;
    QAction *actionExport_MIDI;
    QAction *actionExport_MP3;
    QAction *actionLoad_image_2;
    QAction *actionExport;
    QAction *actionLoad_image_3;
    QAction *actionImport_from_scanner;
    QWidget *centralWidget;
    QVBoxLayout *verticalLayout;
    QGraphicsView *graphicsView;
    QHBoxLayout *horizontalLayout;
    QProgressBar *progressBar;
    QPushButton *pushButton;
    QMenuBar *menuBar;
    QMenu *menuFile;
    QMenu *menuLoad;
    QMenu *menuSettings;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(901, 634);
        actionLoad_image = new QAction(MainWindow);
        actionLoad_image->setObjectName(QStringLiteral("actionLoad_image"));
        actionScan_image = new QAction(MainWindow);
        actionScan_image->setObjectName(QStringLiteral("actionScan_image"));
        actionOpen_image = new QAction(MainWindow);
        actionOpen_image->setObjectName(QStringLiteral("actionOpen_image"));
        actionExport_MIDI = new QAction(MainWindow);
        actionExport_MIDI->setObjectName(QStringLiteral("actionExport_MIDI"));
        actionExport_MP3 = new QAction(MainWindow);
        actionExport_MP3->setObjectName(QStringLiteral("actionExport_MP3"));
        actionLoad_image_2 = new QAction(MainWindow);
        actionLoad_image_2->setObjectName(QStringLiteral("actionLoad_image_2"));
        actionExport = new QAction(MainWindow);
        actionExport->setObjectName(QStringLiteral("actionExport"));
        actionLoad_image_3 = new QAction(MainWindow);
        actionLoad_image_3->setObjectName(QStringLiteral("actionLoad_image_3"));
        actionImport_from_scanner = new QAction(MainWindow);
        actionImport_from_scanner->setObjectName(QStringLiteral("actionImport_from_scanner"));
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        verticalLayout = new QVBoxLayout(centralWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        graphicsView = new QGraphicsView(centralWidget);
        graphicsView->setObjectName(QStringLiteral("graphicsView"));

        verticalLayout->addWidget(graphicsView);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        progressBar = new QProgressBar(centralWidget);
        progressBar->setObjectName(QStringLiteral("progressBar"));
        progressBar->setValue(24);

        horizontalLayout->addWidget(progressBar);

        pushButton = new QPushButton(centralWidget);
        pushButton->setObjectName(QStringLiteral("pushButton"));

        horizontalLayout->addWidget(pushButton);


        verticalLayout->addLayout(horizontalLayout);

        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 901, 22));
        menuFile = new QMenu(menuBar);
        menuFile->setObjectName(QStringLiteral("menuFile"));
        menuLoad = new QMenu(menuFile);
        menuLoad->setObjectName(QStringLiteral("menuLoad"));
        menuSettings = new QMenu(menuBar);
        menuSettings->setObjectName(QStringLiteral("menuSettings"));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        menuBar->addAction(menuFile->menuAction());
        menuBar->addAction(menuSettings->menuAction());
        menuFile->addAction(menuLoad->menuAction());
        menuFile->addAction(actionExport);
        menuLoad->addAction(actionLoad_image_3);
        menuLoad->addAction(actionImport_from_scanner);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", 0));
        actionLoad_image->setText(QApplication::translate("MainWindow", "Load image", 0));
        actionScan_image->setText(QApplication::translate("MainWindow", "Scan image", 0));
        actionOpen_image->setText(QApplication::translate("MainWindow", "Open image", 0));
        actionExport_MIDI->setText(QApplication::translate("MainWindow", "Export MIDI", 0));
        actionExport_MP3->setText(QApplication::translate("MainWindow", "Export MP3", 0));
        actionLoad_image_2->setText(QApplication::translate("MainWindow", "Load image", 0));
        actionExport->setText(QApplication::translate("MainWindow", "Export", 0));
        actionLoad_image_3->setText(QApplication::translate("MainWindow", "Load image", 0));
        actionImport_from_scanner->setText(QApplication::translate("MainWindow", "Import from scanner", 0));
        pushButton->setText(QApplication::translate("MainWindow", "Start Conversion", 0));
        menuFile->setTitle(QApplication::translate("MainWindow", "File", 0));
        menuLoad->setTitle(QApplication::translate("MainWindow", "Load", 0));
        menuSettings->setTitle(QApplication::translate("MainWindow", "Settings", 0));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
