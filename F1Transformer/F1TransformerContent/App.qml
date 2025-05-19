import QtQuick
import F1Transformer


Window {
    width: mainScreen.width
    height: mainScreen.height

    visible: true
    title: "F1Transformer"

    F1TransformerView {
        id: mainScreen
    }

}

