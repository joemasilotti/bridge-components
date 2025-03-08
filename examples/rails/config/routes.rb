Rails.application.routes.draw do
  resources :components, only: :index

  namespace :components do
    resource :alert, only: :show do
      collection do
        get :result
      end
    end

    resource :button, only: :show do
      collection do
        get :text
        get :image
        get :result
      end
    end

    resource :form, only: %i[new create show]

    resource :menu, only: :show do
      collection do
        get :result
      end
    end
  end

  root "components#index"
end
