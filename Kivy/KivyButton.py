from kivymd.app import MDApp
from kivymd.uix.screen import Screen
from kivymd.uix.label import MDIcon
from kivymd.uix.button import MDFlatButton,MDRectangleFlatButton,MDIconButton

class DemoApp(MDApp):
    def build(self):
        screen = Screen()
        btn_flat = MDRectangleFlatButton(text='Button',pos_hint={'center_x':0.5,'center_y':0.5})
        icon_btn = MDIconButton(icon='android')
        icon = MDIcon(icon='language-python',pos_hint={'center_x':0.5,'center_y':0.5})
        screen.add_widget(btn_flat)
        screen.add_widget(icon)
        screen.add_widget(icon_btn)
        return screen

DemoApp().run()