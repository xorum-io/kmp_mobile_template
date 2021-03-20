import Foundation
import UIKit

extension String {

    var localized: String {
        let string = NSLocalizedString(self, comment: "")

        if
            string == self, //the translation was not found
            let baseLanguagePath = Bundle.main.path(forResource: "Base", ofType: "lproj"),
            let baseLangBundle = Bundle(path: baseLanguagePath) {

            return NSLocalizedString(self, bundle: baseLangBundle, comment: "")
        } else {
            return string
        }
    }
    
    func localizedFormat(args: CVarArg...) -> String {
        let format = self.localized
        return String(format: format, arguments: args)
    }
}
