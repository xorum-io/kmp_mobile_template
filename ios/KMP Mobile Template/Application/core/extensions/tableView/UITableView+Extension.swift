import Foundation
import UIKit

extension UITableView {

    func scrollToTop(_ animated: Bool) {
        self.setContentOffset(CGPoint.zero, animated: animated)
    }

    func scrollToBottom(animated: Bool = false) {
        self.scrollRectToVisible(CGRect(x: 0, y: contentSize.height - bounds.size.height, width: bounds.size.width, height: bounds.size.height), animated: false)
    }

    func registerForReuse<T: UITableViewCell>(cellNibType: T.Type) {
        register(UINib.init(nibName: cellNibType.nibName, bundle: nil), forCellReuseIdentifier: cellNibType.reuseIdentifier)
    }

    func registerForReuse<T: UITableViewCell>(cellType: T.Type) {
        register(cellType, forCellReuseIdentifier: cellType.reuseIdentifier)
    }

    func dequeueReusableCell<T: UITableViewCell>(cellNibType: T.Type) -> T {
        return dequeueReusableCell(withIdentifier: cellNibType.reuseIdentifier) as! T
    }

    func dequeueReusableCell<T: UITableViewCell>(cellType: T.Type) -> T {
        return dequeueReusableCell(withIdentifier: cellType.reuseIdentifier) as! T
    }
}
