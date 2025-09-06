module Components
  class ButtonsController < ApplicationController
    def show
    end

    def text
    end

    def image
    end

    def result
      @side = params[:side]
    end
  end
end
