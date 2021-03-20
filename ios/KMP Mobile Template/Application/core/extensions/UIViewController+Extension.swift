import Foundation
import UIKit

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
}
