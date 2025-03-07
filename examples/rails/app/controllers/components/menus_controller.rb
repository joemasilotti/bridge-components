module Components
  class MenusController < ApplicationController
    def show
    end

    def result
      @item = params[:item]
    end
  end
end
