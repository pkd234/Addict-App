package com.pankaj.addict.interfaces;

import com.pankaj.addict.models.AddictModel;

public interface CreateAddictInterface {
    void onItemCreated(AddictModel addictModel);

    void onNegativeResponse();

    void onPositiveResponse();
}
