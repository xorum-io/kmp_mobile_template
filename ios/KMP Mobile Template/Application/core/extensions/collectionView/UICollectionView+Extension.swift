import Foundation
import UIKit

extension UICollectionView {

    func scrollToTop(_ animated:Bool){
        self.setContentOffset(CGPoint.zero, animated: animated)
    }

    func scrollToBottom(animated: Bool = false) {
        self.scrollRectToVisible(CGRect(x: 0, y: contentSize.height - bounds.size.height, width: bounds.size.width, height: bounds.size.height), animated: false)
    }

    func registerForReuse<T: UICollectionViewCell>(cellNibType: T.Type) {
        register(UINib.init(nibName: cellNibType.nibName, bundle: nil), forCellWithReuseIdentifier: cellNibType.reuseIdentifier)
    }

    func registerForReuse<T: UICollectionViewCell>(cellType: T.Type) {
        register(cellType, forCellWithReuseIdentifier: cellType.reuseIdentifier)
    }

    func dequeueReusableCell<T: UICollectionViewCell>(cellNibType: T.Type, for indexPath: IndexPath) -> T {
        return dequeueReusableCell(withReuseIdentifier: cellNibType.reuseIdentifier, for: indexPath) as! T
    }

    func dequeueReusableCell<T: UICollectionViewCell>(cellType: T.Type, for indexPath: IndexPath) -> T {
        return dequeueReusableCell(withReuseIdentifier: cellType.reuseIdentifier, for: indexPath) as! T
    }
}
