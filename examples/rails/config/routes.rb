Rails.application.routes.draw do
  resources :components, only: :index

  namespace :components do
    resource :alert, only: :show do
      collection do
        get :result
      end
    end

    resource :barcode_scanner, only: :show

    resource :biometrics_lock, only: :show

    resource :button, only: :show do
      collection do
        get :text
        get :image
        get :left
        get :result
      end
    end

    resource :document_scanner, only: :show

    resource :form, only: %i[new create show]

    resource :haptic, only: :show

    resource :location, only: :show

    resource :menu, only: :show do
      collection do
        get :result
      end
    end

    resource :nfc, only: :show

    resource :notification_token, only: :show

    resource :permissions, only: :show

    resource :review_prompt, only: :show

    resources :searches, only: %i[index show]

    resource :share, only: :show do
      collection do
        get :current
        get :custom
      end
    end

    resources :tabs, except: :show

    resource :theme, only: :show

    resource :toast, only: :show
  end

  resource :configurations, only: [] do
    get "ios"
    get "android"
  end

  root "components#index"
end
