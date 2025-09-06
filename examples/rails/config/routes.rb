Rails.application.routes.draw do
  resources :components, only: :index

  resources :configurations, only: [] do
    get :ios, on: :collection
  end

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
        get :two
        get :result
      end
    end

    resource :form, only: %i[new create show]

    resource :haptic, only: :show

    resource :menu, only: :show do
      collection do
        get :result
      end
    end

    resource :review_prompt, only: :show

    resources :searches, only: %i[index show]

    resource :share, only: :show

    resource :theme, only: :show

    resource :toast, only: :show
  end

  root "components#index"
end
