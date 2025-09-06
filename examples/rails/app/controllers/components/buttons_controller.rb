module Components
  class ButtonsController < ApplicationController
    def result
      @side = params[:side]
    end
  end
end
