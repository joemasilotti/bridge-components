class Tab < ApplicationRecord
  validates :title, :path, :ios_image, :android_image, presence: true

  def self.components_tab
    new(
      title: "Components",
      path: Rails.application.routes.url_helpers.components_path,
      ios_image: "puzzlepiece",
      ios_selected_image: "puzzlepiece.fill",
      android_image: "TODO"
    )
  end

  def self.tabs_tab
    new(
      title: "Tabs",
      path: Rails.application.routes.url_helpers.components_tabs_path,
      ios_image: "circle.grid.2x1",
      ios_selected_image: "circle.grid.2x1.right.filled",
      android_image: "TODO"
    )
  end
end
