# coding=utf-8
from skfuzzy import control as ctrl
import numpy as np
import skfuzzy as fuzz

# old ticket number is tv-6149
# variant 10 - smartphones classification

recommendation = ctrl.Consequent(np.arange(0, 100, 1), 'recommendation')
recommendation.automf(7, 'quant',
                      ["awful", "cheap_phone", "good_deal", "mid_phone", "for_photos", "for_gamers", "flagman"])

price = ctrl.Antecedent(np.arange(100., 50000., 1), 'price')
price['cheap'] = fuzz.zmf(price.universe, 5000, 15000)
price['mid'] = fuzz.gaussmf(price.universe, 15000, 7000)
price['expensive'] = fuzz.smf(price.universe, 15000, 30000)

screen_size = ctrl.Antecedent(np.arange(0., 10., 0.2), 'screen_size')
screen_size['small'] = fuzz.zmf(screen_size.universe, 3, 5)
screen_size['mid'] = fuzz.gaussmf(screen_size.universe, 5, 1)
screen_size['large'] = fuzz.smf(screen_size.universe, 5.5, 6.5)

camera = ctrl.Antecedent(np.arange(0., 200., 0.5), 'camera')
camera['bad'] = fuzz.zmf(camera.universe, 14, 16)
camera['mid'] = fuzz.trimf(camera.universe, [15, 20, 30])
camera['good'] = fuzz.trimf(camera.universe, [30, 40, 60])
camera['too many'] = fuzz.smf(camera.universe, 60, 70)

actuality = ctrl.Antecedent(np.arange(2010., 2021., 0.5), 'actuality')
actuality['new'] = fuzz.smf(actuality.universe, 2020.5, 2021)
actuality['normal'] = fuzz.gaussmf(actuality.universe, 2020, 0.5)
actuality['old'] = fuzz.gaussmf(actuality.universe, 2019, 0.5)
actuality['ancient'] = fuzz.zmf(actuality.universe, 2018, 2019)

ram = ctrl.Antecedent(np.arange(0., 40., 0.5), 'ram')
ram['brick'] = fuzz.zmf(ram.universe, 0, 3)
ram['dumb'] = fuzz.trimf(ram.universe, [1, 4, 6])
ram['normal'] = fuzz.trimf(ram.universe, [4, 8, 12])
ram['robust'] = fuzz.trimf(ram.universe, [8, 15, 20])
ram['almighty'] = fuzz.smf(ram.universe, 16, 20)


cores = ctrl.Antecedent(np.arange(0., 20., 1), 'processor')
cores['brick'] = fuzz.zmf(cores.universe, 0, 4)
cores['dumb'] = fuzz.trimf(cores.universe, [2, 4, 6])
cores['normal'] = fuzz.trimf(cores.universe, [4, 8, 12])
cores['games'] = fuzz.smf(cores.universe, 8, 12)


price.view()
screen_size.view()
camera.view()
actuality.view()
ram.view()
cores.view()


cheap_phone = ctrl.Rule(price['cheap'] & camera['bad'] & actuality["normal"] & ram["dumb"] & cores["dumb"],
                        recommendation["cheap_phone"])
good_deal = ctrl.Rule(price['cheap'] & camera['good'] & actuality["normal"] & ram["normal"] & cores["normal"],
                      recommendation["good_deal"])
mid_phone = ctrl.Rule(price['cheap'] & camera['good'] & actuality["normal"] & ram["normal"] & cores["normal"],
                      recommendation["mid_phone"])
for_photos = ctrl.Rule(camera["too many"] & ram["robust"] & cores["normal"], recommendation["for_photos"])
for_gamers = ctrl.Rule(screen_size["large"] & actuality["normal"] & ram["almighty"] & cores["games"],
                       recommendation["for_gamers"])
flagman = ctrl.Rule(price['expensive'] & camera['good'] & actuality["new"] & ram["almighty"] & cores["games"],
                    recommendation["flagman"])

awful = ctrl.Rule(price['expensive'] & (camera['mid'] | camera['bad']) &
                  ram["dumb"] & cores["dumb"], recommendation["awful"])

smartphones_classification_ctrl = ctrl.ControlSystem(
    [cheap_phone, good_deal, mid_phone, for_photos, for_gamers, flagman, awful])
classification = ctrl.ControlSystemSimulation(smartphones_classification_ctrl)

classification.input['price'] = 13000
classification.input['screen_size'] = 5.5
classification.input['camera'] = 13
classification.input['actuality'] = 2019
classification.input['ram'] = 4
classification.input['processor'] = 4
classification.compute()

print(classification.output['recommendation'])
recommendation.view(sim=classification)


classification.input['price'] = 40000
classification.input['screen_size'] = 6
classification.input['camera'] = 13
classification.input['actuality'] = 2021
classification.input['ram'] = 32
classification.input['processor'] = 16
classification.compute()

print(classification.output['recommendation'])
recommendation.view(sim=classification)

# smartphones_classification_ctrl.view();

classification.input['price'] = 550000
classification.input['screen_size'] = 5
classification.input['camera'] = 2
classification.input['actuality'] = 2016
classification.input['ram'] = 4
classification.input['processor'] = 4
classification.compute()

print(classification.output['recommendation'])
recommendation.view(sim=classification)
