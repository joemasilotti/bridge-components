class CreateTabs < ActiveRecord::Migration[8.0]
  def change
    create_table :tabs do |t|
      t.string :title, null: false
      t.string :path, null: false
      t.string :ios_image, null: false
      t.string :ios_selected_image
      t.string :android_image, null: false

      t.timestamps
    end
  end
end
