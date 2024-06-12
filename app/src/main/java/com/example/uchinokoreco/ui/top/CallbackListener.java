package com.example.uchinokoreco.ui.top;

import com.example.uchinokoreco.data.entities.PetsList;

public interface CallbackListener {

    void moveToDiariesFragment(PetsList petsList);

    void changeTitle(String title);
}
