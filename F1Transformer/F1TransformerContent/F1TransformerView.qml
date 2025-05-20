

import QtQuick
import QtQuick.Controls
import QtQuick3D
import QtQuick3D.Effects
import F1Transformer
import Generated.QtQuick3D.Scene

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height
    color: Constants.backgroundColor

    property int animationTrigger: 0
    property bool isPlaying: scene1.isPlaying


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
                x: 2.214
                y: 96.83
                z: 445.36588
            }

            Scene {
                id: scene1
                animationTrigger: rectangle.animationTrigger
                scale: "80,80,60"
                x: 2.456
                y: 0
                z: 188.52213
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
    D{i:0}D{i:3;cameraSpeed3d:25;cameraSpeed3dMultiplier:1}
}
##^##*/

