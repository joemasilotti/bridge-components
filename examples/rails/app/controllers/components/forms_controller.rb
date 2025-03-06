module Components
  class FormsController < ApplicationController
    def new
      @form = Form.new
    end

    def create
      sleep 2 # Simulate slow submission to show disabled button.

      @form = Form.new(form_params)
      if @form.valid?
        redirect_to components_form_path(name: @form.name)
      else
        render :new, status: :unprocessable_entity
      end
    end

    def show
      @name = params[:name]
    end

    private

    def form_params
      params.require(:form).permit(:name)
    end
  end
end
