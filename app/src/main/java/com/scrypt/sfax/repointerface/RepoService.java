package com.scrypt.sfax.repointerface;

import com.scrypt.sfax.data.Authenticate;
import com.scrypt.sfax.data.Fax;
import com.scrypt.sfax.data.FaxNumber;
import com.scrypt.sfax.data.Folder;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;

/* interface between ViewModel/UI and RepoManager */
public interface RepoService {

  Single<Authenticate> getAuthentication(String request);

  Maybe<List<FaxNumber>> getFaxNumbers(String userToken);

  Single<List<Folder>> getFolders(String userToken, String numberId);

  Single<List<Fax>> getFaxList(String userToken, String folderId);
}
