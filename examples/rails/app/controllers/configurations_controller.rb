class ConfigurationsController < ApplicationController
  def ios
    render json: {
      settings: {},
      rules: [
        {
          patterns: [
            "components/button/two"
          ],
          properties: {
            context: "modal"
          }
        }
      ]
    }
  end
end
