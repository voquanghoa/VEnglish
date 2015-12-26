package com.qhvv.englishforalllevel.constant;

/**
 * Created by Vo Quang Hoa on 12/20/2015.
 */
public interface AppConstant {
    String JSON_DATA_FILE = "data.json";
    String SERVER_BASE_PATH = "http://doanit.com/english4all/";

    String GRAMMAR_FOLDER = "grammar/";
    String EXAMINATION_FOLDER = "examination/";

    String GRAMMAR_JSON_PATH = SERVER_BASE_PATH + GRAMMAR_FOLDER + JSON_DATA_FILE;
    String EXAMINATION_JSON_PATH = SERVER_BASE_PATH + EXAMINATION_FOLDER + JSON_DATA_FILE;

    String MESSAGE_FILE_NAME = "file_name";
    String MESSAGE_FOLDER = "category";

    String USER_RESULT_FILE = "user_result.json";
    String CHARSET = "UTF-8";
}
