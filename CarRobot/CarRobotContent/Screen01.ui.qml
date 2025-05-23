/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/

import QtQuick
import QtQuick.Controls
import QtQuick3D
import QtQuick3D.Effects
import CarRobot
import Generated.QtQuick3D.Scene

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height

    color: Constants.backgroundColor
    property string currentState: "transform_to_vehicle_hero_timeline"

    View3D {
        id: view3D
        anchors.fill: parent

        environment: sceneEnvironment

        SceneEnvironment {
            id: sceneEnvironment
            antialiasingMode: SceneEnvironment.MSAA
            antialiasingQuality: SceneEnvironment.High
        }

        Node {
            id: scene
            DirectionalLight {
                id: directionalLight
            }

            PerspectiveCamera {
                id: sceneCamera
                x: -0
                y: 68.631
                z: 350
            }

            Scene {
                id: scene1
                x: 1.96
                y: 0
                state: rectangle.currentState
                z: 19.01318
            }
        }
    }

    Item {
        id: __materialLibrary__
        PrincipledMaterial {
            id: defaultMaterial
            objectName: "Default Material"
            baseColor: "#4aee45"
        }
    }
}

/*##^##
Designer {
    D{i:0;matPrevEnvDoc:"SkyBox";matPrevEnvValueDoc:"preview_studio";matPrevModelDoc:"#Sphere"}
D{i:3;cameraSpeed3d:25;cameraSpeed3dMultiplier:1}
}
##^##*/
