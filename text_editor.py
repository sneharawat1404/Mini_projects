from tkinter import *
from tkinter.filedialog import askopenfilename, asksaveasfilename

text_editor = Tk()
text_editor.title("Text Editor")

text_area = Text(text_editor, fg='white', bg='dark blue', font='Arial 12')
text_area.pack()

def open_file():
    file_path = askopenfilename(filetypes=[("Text file", "*.txt"), ("All Files", "*.*")])
    if not file_path:
        return
    text_area.delete("1.0", END)
    with open(file_path, mode='r', encoding='utf-8') as input_file:
        text = input_file.read()
        text_area.insert(END, text)
        text_editor.title(f'Simple Text Editor - {file_path}')

def save_file():
    file_path = asksaveasfilename(defaultextension='.txt', filetypes=[("Text Files", "*.txt"), ("All Files", "*.*")])
    if not file_path:
        return
    with open(file_path, mode='w', encoding='utf-8') as output_file:
        text = text_area.get('1.0', END)
        output_file.write(text)
        text_editor.title(f'Simple Text Editor - {file_path}')

menu_bar = Menu(text_editor)
text_editor.config(menu=menu_bar)
file_menu = Menu(menu_bar)

menu_bar.add_cascade(label='File', menu=file_menu)
file_menu.add_command(label='Open', command=open_file)
file_menu.add_command(label='Save As...', command=save_file)

text_editor.mainloop()
