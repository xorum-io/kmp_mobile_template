import Foundation
import UIKit
import PKHUD

extension UIViewController {

    func presentModal(_ viewController: UIViewController, withNavigation: Bool = true, animated: Bool = true) {
        var viewController = viewController
        
        if withNavigation {
            viewController = UINavigationController(rootViewController: viewController)
        }
        
        present(viewController.apply() {
            $0.modalPresentationStyle = .fullScreen
            $0.modalTransitionStyle = .coverVertical
        }, animated: animated)
    }
    
    func showLoading(userInteractionOnUnderlyingViewsEnabled: Bool = false) {
        PKHUD.sharedHUD.userInteractionOnUnderlyingViewsEnabled = userInteractionOnUnderlyingViewsEnabled
        HUD.show(.progress, onView: UIApplication.shared.windows.last { $0.isKeyWindow })
    }

    func hideLoading() {
        HUD.hide(afterDelay: 0)
    }
}
