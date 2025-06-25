module Components
  class SearchesController < ApplicationController
    def index
      @items = [
        "Barbara Walker Crossing",
        "Blumenauer Bridge",
        "BNSF Railway Bridge 5.1",
        "Broadway Bridge",
        "Burnside Bridge",
        "Cedar Crossing Bridge",
        "Eastbank Esplanade",
        "Fremont Bridge",
        "Gibbs Street Pedestrian Bridge",
        "Hawthorne Bridge",
        "Madison Street Bridge",
        "Marquam Bridge",
        "Morrison Bridge",
        "Ned Flanders Crossing",
        "Oregon Slough Railroad Bridge",
        "Pedestrian Bridge",
        "Rhine–Lafayette Pedestrian Overpass",
        "Ross Island Bridge",
        "Sellwood Bridge",
        "St. Johns Bridge",
        "Steel Bridge",
        "Tilikum Crossing",
        "Vista Bridge"
      ]
    end

    def show
      @result = params[:id]
    end
  end
end
