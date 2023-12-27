#include <iostream>
#include <cstdlib>

struct ToDo {
    char data[100];
    ToDo* link;
    int count;
};

ToDo* start = nullptr;

void welcomeUser();
void seeToDo();
void createToDo();
void delToDo();
void fixCount();
void updateToDo();

int main() {
    int choice;
    welcomeUser();
    while (true) {
        system("color 3F");
        system("cls");
        std::cout << "\n1. See Your ToDo List";
        std::cout << "\n2. Create Your ToDos";
        std::cout << "\n3. Delete Your ToDos";
        std::cout << "\n4. Update Your ToDos";
        std::cout << "\n5. Exit";
        std::cout << "\n\nEnter your choice: ";
        std::cin >> choice;

        switch (choice) {
            case 1:
                seeToDo();
                break;
            case 2:
                createToDo();
                break;
            case 3:
                delToDo();
                break;
            case 4:
                updateToDo();
                break;
            case 5:
                exit(0);
        }
    }

    return 0;
}

void welcomeUser() {
    system("color 4F");
    std::cout << "\n\n\n\n\n";
    std::cout << "\t------------------------------------------------------------------------------------------------------\n\n";
    std::cout << "\t#################################### YOUR TODO LIST APP ##############################################\n\n";
    std::cout << "\t------------------------------------------------------------------------------------------------------";
    std::cout << "\n\n\n\t\t*******************************WELCOME*******************************\n\n\n\n\n\n\n\n\n\t";
    system("pause");
}

void seeToDo() {
    system("cls");
    ToDo* temp = start;

    if (start == nullptr) {
        std::cout << "\nEmpty TODO\n\n";
    }

    while (temp != nullptr) {
        std::cout << temp->count << ")";
        std::cout << temp->data << std::endl;
        std::cin.ignore();
        temp = temp->link;
    }

    std::cout << "\n\n\n";
    system("pause");
}

void createToDo() {
    char k;
    ToDo* t;
    ToDo* temp;

    system("cls");
    while (true) {
        std::cout << "\nWant to add? y/n: ";
        std::cin >> k;

        if (k == 'n') {
            break;
        } else {
            if (start == nullptr) {
                t = new ToDo;
                start = t;
                std::cout << "\nADD it..\n";
                std::cin.ignore();
                std::cin.getline(t->data, 100);
                t->count = 1;
                start->link = nullptr;
            } else {
                temp = new ToDo;
                std::cout << "\nADD it..\n";
                std::cin.ignore();
                std::cin.getline(temp->data, 100);
                temp->link = nullptr;
                t->link = temp;
                t = t->link;
            }
            fixCount();
        }
    }
}

void delToDo() {
    system("cls");
    int d;
    ToDo* temp1 = start;
    ToDo* temp = start->link;

    std::cout << "\nEnter the no of the todo you want to remove: ";
    std::cin >> d;

    while (true) {
        if (temp1->count == d) {
            start = start->link;
            delete temp1;
            fixCount();
            break;
        }

        if (temp->count == d) {
            temp1->link = temp->link;
            delete temp;
            fixCount();
            break;
        } else {
            temp1 = temp;
            temp = temp->link;
        }
    }

    system("pause");
}

void fixCount() {
    ToDo* temp = start;
    int i = 1;

    while (temp != nullptr) {
        temp->count = i;
        i++;
        temp = temp->link;
    }
}

void updateToDo() {
    system("cls");
    ToDo* temp;
    ToDo* t;
    char k;

    while (true) {
        std::cout << "\nWant to add? y/n: ";
        std::cin >> k;

        if (k == 'n') {
            break;
        }

        std::cout << "\nADD it..\n";
        temp = new ToDo;
        std::cin.ignore();
        std::cin.getline(temp->data, 100);
        temp->link = nullptr;
        t = start;

        while (t->link != nullptr) {
            t = t->link;
        }

        t->link = temp;
        fixCount();
    }

    std::cout << "\n\n";
    system("pause");
}
