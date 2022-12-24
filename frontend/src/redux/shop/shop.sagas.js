import { takeLatest, call, put, all } from 'redux-saga/effects';
import {
  getShopDataFromDB,
  convertShopDataToMap
} from '../../shopdatabase/shopdatabase.utils';
import {
  fetchCollectionsSuccess,
  fetchCollectionsFailure
} from './shop.actions';
import ShopActionTypes from './shop.types';

export function* fetchCollectionsAsync() {
  try {
    const snapshot = yield getShopDataFromDB();
    const collectionsMap = yield call(
      convertShopDataToMap,
      snapshot
    );
    yield put(fetchCollectionsSuccess(collectionsMap));
  } catch (error) {
    yield put(fetchCollectionsFailure(error.message));
  }
}

export function* fetchCollectionsStart() {
  yield takeLatest(
    ShopActionTypes.FETCH_COLLECTIONS_START,
    fetchCollectionsAsync
  );
}

export function* shopSagas() {
  yield all([call(fetchCollectionsStart)]);
}
