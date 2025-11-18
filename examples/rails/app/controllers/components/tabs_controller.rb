module Components
  class TabsController < ApplicationController
    def index
      @tabs = [Tab.components_tab, Tab.tabs_tab] + Tab.all
    end

    def new
      @tab = Tab.new
    end

    def create
      @tab = Tab.new(tab_params)
      if @tab.save
        redirect_to components_tabs_path
      else
        render :new, status: :unprocessable_entity
      end
    end

    def edit
      @tab = Tab.find(params[:id])
    end

    def update
      @tab = Tab.find(params[:id])
      if @tab.update(tab_params)
        redirect_to components_tabs_path
      else
        render :edit, status: :unprocessable_entity
      end
    end

    def destroy
      Tab.find(params[:id]).destroy
      redirect_to components_tabs_path
    end

    private

    def tab_params
      params.require(:tab).permit(:title, :path, :ios_image, :android_image)
    end
  end
end
