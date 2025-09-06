module Components
  class MenusController < ApplicationController
    def result
      @item = params[:item]
    end
  end
end
