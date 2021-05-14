import UIKit
import common
import Toast_Swift

class IosMessageHandler: MessageHandler {

    func handle(message: String?) {
        UIApplication.shared.windows.last { $0.isKeyWindow }?.makeToast(message)
    }
}
