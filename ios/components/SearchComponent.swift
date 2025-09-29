import HotwireNative
import UIKit

public final class SearchComponent: BridgeComponent {
    override public class var name: String { "search" }

    override public func onReceive(message: Message) {
        guard let event = Event(rawValue: message.event) else { return }

        switch event {
        case .connect:
            addSearchController(via: message)
        }
    }

    fileprivate func updateSearchResults(with query: String?) {
        let data = QueryMessageData(query: query)
        reply(to: Event.connect.rawValue, with: data)
    }

    private let searchController = UISearchController(searchResultsController: nil)
    private lazy var searchResultsUpdater = SearchResultsUpdater(component: self)

    private func addSearchController(via message: Message) {
        guard let message: MessageData = message.data() else { return }

        searchController.searchResultsUpdater = searchResultsUpdater
        if let placeholder = message.placeholder {
            searchController.searchBar.placeholder = placeholder
        }

        viewController?.navigationItem.searchController = searchController
        viewController?.navigationItem.hidesSearchBarWhenScrolling = false
        viewController?.definesPresentationContext = true
    }
}

private extension SearchComponent {
    enum Event: String {
        case connect
    }

    struct MessageData: Decodable {
        let placeholder: String?
    }

    struct QueryMessageData: Encodable {
        let query: String?
    }
}

private class SearchResultsUpdater: NSObject, UISearchResultsUpdating {
    private weak var component: SearchComponent?

    init(component: SearchComponent) {
        self.component = component
    }

    func updateSearchResults(for searchController: UISearchController) {
        component?.updateSearchResults(with: searchController.searchBar.text)
    }
}
