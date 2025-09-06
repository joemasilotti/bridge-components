# This file is used by Rack-based servers to start the application.

require_relative "config/environment"

root = Rails.root.join("..", "..").expand_path.to_s
map "/bridge-components" do
  run Rack::Files.new(root)
end

run Rails.application
Rails.application.load_server
