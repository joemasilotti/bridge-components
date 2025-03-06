Rails.application.routes.draw do
  resources :components, only: :index

  namespace :components do
    resource :button, only: :show do
      collection do
        get :text
        get :image
        get :result
      end
    end

    resource :form, only: :show do
      collection do
        get :title
        get :image
      end
    end
  end

  root "components#index"
end
