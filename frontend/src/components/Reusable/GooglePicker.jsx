/* global gapi */

import { Box, Button } from '@mui/material';
import { useGoogleLogin } from '@react-oauth/google';
import React, { useState } from 'react';
import useDrivePicker from 'react-google-drive-picker';

const CLIENT_ID = "617937755851-3lv9ffdfi5tc11v349e0a79p943udahn.apps.googleusercontent.com";
const CLIENT_SECRET = "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";
const REDIRECT_URI = 'https://developers.google.com/oauthplayground';
const REFRESH_TOKEN = '1//04BulIHnYwTbDCgYIARAAGAQSNwF-L9IrM_ysKW9eXYhN1HBfOQCbysrrknTr_ZNDKylCObtiDQhehPJlEpQJWDq3kgv0WoEo1i0';
const SCOPES = ['https://www.googleapis.com/auth/drive.readonly']
const API_KEY = 'AIzaSyBCkMBdDRgz0UKLxtaqj1eNbXL9_uiH8jc'

const GooglePicker = () => {
  const [openPicker] = useDrivePicker();
  const [modal, setModal] = useState(false);
  const [errorModal, setErrorModal] = useState(false)

    const closeModal = () => {
        setModal(false);
    }

    const closeErrorModal = () => {
        setErrorModal(false);
    }
  
  const handleOpenPicker = (access_token) => {
    gapi.load('client:auth2', () => {
      gapi.client
        .init({
          apiKey: API_KEY,
        })
        .then(() => {
          const pickerConfig = {
            clientId: CLIENT_ID,
            developerKey: API_KEY,
            viewId: 'DOCS',
            showUploadView: true,
            token: access_token,
            showUploadFolders: true,
            supportDrives: true,
            multiselect: false,
            customScopes: SCOPES,
            callbackFunction: (data) => {
                console.log(data);
                if(data.action !== "picked")
                    return;
                console.log(data);
                console.log(data.docs.map((item) => item.url));    

                fetch("https://www.googleapis.com/drive/v3/files/" + data.docs[0].id + "/permissions", {
                    method: "POST",
                    headers: {
                      "Authorization": "Bearer " + access_token,
                      "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        role: "reader",
                        type: "anyone"
                      }),
                }).then((response) => {
                    console.log(response);
                })
                // request2.execute(function(resp) {
                //     console.log(resp);
                // });
            },
          };
          openPicker(pickerConfig);
        });
    });
  };

  const googleLogin = useGoogleLogin({
    onSuccess: async (response) => {
        setModal(true);
        try{
            handleOpenPicker(response.access_token)
            closeModal();
        }catch (error) {
            setErrorModal(true);
            closeModal();
            setTimeout(() => closeErrorModal(), 1000);
        }
    },
    onError: error => {
        console.log(error)
        setErrorModal(true);
        closeModal();
        setTimeout(() => closeErrorModal(), 1000);
    },
});

  return (
    <Box>
          <Button variant="outlined" size="large" onClick={googleLogin} >Upload A </Button>
    </Box>
  );
};

export default GooglePicker;