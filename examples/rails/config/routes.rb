Rails.application.routes.draw do
  resources :components, only: :index do
    collection do
      get :button
    end
  end

  root "components#index"
end
