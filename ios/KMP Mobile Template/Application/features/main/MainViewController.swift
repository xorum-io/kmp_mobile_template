import UIKit
import TinyConstraints
import common

class MainViewController: BaseViewController, ReKampStoreSubscriber {

    private let label = UILabel().apply {
        $0.font = Font.body
        $0.textColor = Palette.colorPrimary
    }

    required init() {
        super.init(nibName: nil, bundle: nil)
        setupView()
    }

    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private func setupView() {
        view.backgroundColor = Palette.white

        buildViewTree()
        setConstraints()
    }

    private func buildViewTree() {
        [label].forEach(view.addSubview)
    }

    private func setConstraints() {
        label.centerInSuperview()
    }
    
    func onNewState(state: Any) {
        let state = state as! SpaceState

        switch(state.status) {
        case .idle:
            label.text = "\(state.peopleInSpace?.number ?? 0)"
        case .pending:
            label.text = "Loading ..."
        default:
            break
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)

        store.subscribe(subscriber: self) { subscription in
            subscription.skipRepeats { oldState, newState in
                return KotlinBoolean(bool: oldState.space == newState.space)
            }.select { state in
                return state.space
            }
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        store.unsubscribe(subscriber: self)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        store.dispatch(action: SpaceRequests.FetchPeopleInSpace())
    }
}
