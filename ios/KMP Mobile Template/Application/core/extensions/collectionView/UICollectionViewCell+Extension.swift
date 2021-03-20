import Foundation
import UIKit

extension UICollectionViewCell {

    class var nibName: String {
        return String(describing: self)
    }

    class var reuseIdentifier: String {
        return String(describing: self)
    }

    class func register(to collectionView: UICollectionView) {
        collectionView.register(UINib.init(nibName: self.nibName, bundle: nil), forCellWithReuseIdentifier: self.reuseIdentifier)
    }
}
