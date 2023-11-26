#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <locale>
#include <iomanip>

using namespace std;

class RPGCharacter {
public:
    string name;
    int strength;
    int agility;
    int endurance;
    int intellect;
    int spirit;

    RPGCharacter(const string& _name, int _strength, int _agility, int _endurance, int _intellect, int _spirit)
        : name(_name), strength(_strength), agility(_agility), endurance(_endurance), intellect(_intellect), spirit(_spirit) {}

    void printCharacterHeader() const {
        cout << "Имя       | Сила | Ловкость | Выносливость | Интеллект | Дух\n";
        cout << "------------------------------------------------------------\n";
    }

    void printCharacter() const {
        cout << left << setw(10) << name << "| " << setw(5) << strength << "| " << setw(9) << agility << "| "
            << setw(14) << endurance << "| " << setw(9) << intellect << "| " << spirit << '\n';
    }
};

// Функция сортировки по силе
bool compareByStrength(const RPGCharacter& char1, const RPGCharacter& char2) {
    return char1.strength > char2.strength;
}

// Функция сортировки по ловкости
bool compareByAgility(const RPGCharacter& char1, const RPGCharacter& char2) {
    return char1.agility > char2.agility;
}

// Функция сортировки по выносливости
bool compareByEndurance(const RPGCharacter& char1, const RPGCharacter& char2) {
    return char1.endurance > char2.endurance;
}

// Функция сортировки по интеллекту
bool compareByIntellect(const RPGCharacter& char1, const RPGCharacter& char2) {
    return char1.intellect > char2.intellect;
}

// Функция сортировки по духу
bool compareBySpirit(const RPGCharacter& char1, const RPGCharacter& char2) {
    return char1.spirit > char2.spirit;
}

// Функция для записи массива персонажей в файл
void writeToFile(const vector<RPGCharacter>& characters, const string& filename) {
    ofstream file(filename);
    if (file.is_open()) {
        file << "Имя,Сила,Ловкость,Выносливость,Интеллект,Дух\n";
        for (const auto& character : characters) {
            file << character.name << ',' << character.strength << ',' << character.agility << ','
                << character.endurance << ',' << character.intellect << ',' << character.spirit << '\n';
        }
        file.close();
        cout << "Массив успешно записан в файл: " << filename << endl;
    }
    else {
        cerr << "Ошибка открытия файла для записи." << endl;
    }
}

int main() {
    setlocale(LC_ALL, "Russian");

    // Создаем массив персонажей
    vector<RPGCharacter> characters = {
        RPGCharacter("Воин", 15, 10, 12, 8, 5),
        RPGCharacter("Плут", 10, 15, 8, 5, 10),
        RPGCharacter("Маг", 5, 8, 6, 15, 12),
        RPGCharacter("Лучник", 12, 13, 10, 7, 8),
        RPGCharacter("Чародей", 8, 6, 9, 14, 11),
        RPGCharacter("Рыцарь", 14, 11, 15, 6, 7),
        RPGCharacter("Вор", 11, 14, 7, 9, 13),
        RPGCharacter("Жрец", 7, 9, 11, 13, 14),
        RPGCharacter("Охотник", 13, 12, 14, 10, 6),
        RPGCharacter("Монах", 9, 7, 13, 11, 15),
        RPGCharacter("Некромант", 6, 5, 5, 12, 9),
        RPGCharacter("Бард", 10, 11, 8, 14, 10),
        RPGCharacter("Паладин", 13, 15, 11, 7, 8),
        RPGCharacter("Друид", 7, 10, 12, 13, 11),
        RPGCharacter("Волшебник", 5, 8, 7, 15, 12),
        // Добавьте еще персонажей по вашему усмотрению
    };

    // Выводим исходный массив
    cout << "Исходный массив:\n";
    characters[0].printCharacterHeader();
    for (const auto& character : characters) {
        character.printCharacter();
    }

    // Сортируем по разным характеристикам
    sort(characters.begin(), characters.end(), compareByStrength);
    cout << "\nМассив после сортировки по силе:\n";
    characters[0].printCharacterHeader();
    for (const auto& character : characters) {
        character.printCharacter();
    }

    sort(characters.begin(), characters.end(), compareByAgility);
    cout << "\nМассив после сортировки по ловкости:\n";
    characters[0].printCharacterHeader();
    for (const auto& character : characters) {
        character.printCharacter();
    }

    sort(characters.begin(), characters.end(), compareByEndurance);
    cout << "\nМассив после сортировки по выносливости:\n";
    characters[0].printCharacterHeader();
    for (const auto& character : characters) {
        character.printCharacter();
    }

    sort(characters.begin(), characters.end(), compareByIntellect);
    cout << "\nМассив после сортировки по интеллекту:\n";
    characters[0].printCharacterHeader();
    for (const auto& character : characters) {
        character.printCharacter();
    }

    sort(characters.begin(), characters.end(), compareBySpirit);
    cout << "\nМассив после сортировки по духу:\n";
    characters[0].printCharacterHeader();
    for (const auto& character : characters) {
        character.printCharacter();
    }

    // Записываем массив в файл
    writeToFile(characters, "rpg_characters.txt");

    return 0;
}
