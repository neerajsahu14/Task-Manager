package com.neerajsahu14.notesapp.presentation.ui.screens

import android.util.Log
import android.view.Window
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogWindowProvider
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.neerajsahu14.notesapp.R
import com.neerajsahu14.notesapp.data.modal.Tasks
import com.neerajsahu14.notesapp.presentation.ui.common.dialogs.AddTaskDialog
import com.neerajsahu14.notesapp.presentation.ui.common.dialogs.ViewTaskDialog
import com.neerajsahu14.notesapp.presentation.ui.event.HomeUiEvents
import com.neerajsahu14.notesapp.presentation.ui.viewmodel.MainActivityViewModel
import com.neerajsahu14.notesapp.ui.theme.*

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainActivityViewModel
) {


    val homeUiState by viewModel.homeUiState.collectAsState()



    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        val (toolbar, logoBox,
            myTaskText, myTaskLazyColum,
            completedTaskText, completedTaskLazyColum,
            addFab) = createRefs()

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = PrimaryLightColor)
            .constrainAs(toolbar) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
            Image(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "Image",
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.CenterEnd
            )
        }

        Box(
            modifier = Modifier
                .size(130.dp)
                .padding(top = 15.dp)
                .background(Color.Transparent)
                .padding(top = (0).dp)
                .constrainAs(logoBox) {},

            ) {
            Image(
                painter = painterResource(id = R.drawable.tm_logo),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxSize()
                    .shadow(
                        clip = true, elevation = 69.dp, shape = CircleShape, spotColor = shadowColor
                    )
            )
        }

        Text(text = "My Tasks",
            fontSize = 21.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .constrainAs(myTaskText) {
                    top.linkTo(logoBox.bottom)
                }
                .constrainAs(myTaskText) {})

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(myTaskLazyColum) {
                top.linkTo(myTaskText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(completedTaskText.top)
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
            }) {
            items(homeUiState.taskList.count()) { item ->
                TaskItem(
                    homeUiState.taskList[item],
                    onTaskCompleted = { checked ->
                        if (checked) {
                            viewModel.homeUiEvent(event = HomeUiEvents.CompleteTasks(item))
                        }
                    },
                    onClick = {
                        viewModel.homeUiEvent(HomeUiEvents.OpenTask(homeUiState.taskList[item]))
                    }
                )
            }
        }

        Text(text = "Completed Tasks", fontSize = 21.sp, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .constrainAs(completedTaskText) {
                top.linkTo(myTaskLazyColum.bottom, margin = 10.dp)
                bottom.linkTo(completedTaskLazyColum.top)
            }
        )

        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(completedTaskLazyColum) {
                top.linkTo(completedTaskText.bottom, margin = 10.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.percent(0.2f)
                bottom.linkTo(parent.bottom)
            }
        ) {
            items(homeUiState.completedTask.count()) { item ->
                CompletedTaskItem(homeUiState.completedTask[item])
            }
        }



        FloatingActionButton(
            containerColor = PrimaryColor,
            contentColor = PrimaryLightColor,
            shape = CircleShape,
            onClick = {
                viewModel.homeUiEvent(event = HomeUiEvents.ToggleDialog)
            },
            modifier = Modifier.constrainAs(addFab) {
                end.linkTo(parent.end, margin = 15.dp)
                bottom.linkTo(parent.bottom, margin = 15.dp)
            }
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }

        // Show the dialog if showDialog is true
        if (homeUiState.showDialog) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.4f))
            ) {
                AddTaskDialog(
                    onDismissRequest = {
                        viewModel.homeUiEvent(event = HomeUiEvents.ToggleDialog)
                    },
                    onAddRequest = { task ->
                        viewModel.homeUiEvent(event = HomeUiEvents.AddTasks(task))
                    }
                )
            }
        }

        if (homeUiState.openTask) {
            homeUiState.selectedTask?.let {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = Color.Black.copy(alpha = 0.4f)
                        )
                ) {
                    ViewTaskDialog(
                        onDismissRequest = {
                            viewModel.homeUiEvent(HomeUiEvents.CloseTaskDialog)
                        },
                        task = it,
                        onCompleteTask = {
                            var index =
                                viewModel.homeUiState.value.taskList.indexOf(
                                    viewModel.homeUiState.value.selectedTask
                                )
                            viewModel.homeUiEvent(event = HomeUiEvents.CompleteTasks(index))
                        })
                }

            }

        }


    }

}


@ReadOnlyComposable
@Composable
fun getDialogWindow(): Window? = (LocalView.current.parent as? DialogWindowProvider)?.window


@Composable
fun TaskItem(
    item: Tasks,
    onTaskCompleted: (Boolean) -> Unit,
    onClick: () -> Unit
) {

    val checkedState = remember { mutableStateOf(false) }

    val checkBoxDefault = CheckboxDefaults.colors(
        checkedColor = PrimaryColor,
        uncheckedColor = PrimaryColor,
        checkmarkColor = PrimaryLightColor,
        disabledCheckedColor = ProgressRed,
        disabledUncheckedColor = ProgressRed,
        disabledIndeterminateColor = ProgressRed,
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(shape = RoundedCornerShape(10.dp), color = TaskBackground)
            .padding(5.dp)
            .clickable {
                onClick()
            }
    ) {

        val (checkBox, taskText, fileIcon, filesCount, progressBar) = createRefs()

        Checkbox(checked = checkedState.value,
            onCheckedChange = onTaskCompleted,
            colors = checkBoxDefault,
            modifier = Modifier.constrainAs(checkBox) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            })

        Text(item.taskName,
            softWrap = false,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(taskText) {
                start.linkTo(checkBox.end, margin = 8.dp)
                end.linkTo(fileIcon.start, margin = 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            })

        Image(painter = painterResource(id = R.drawable.file_icon),
            contentDescription = "file icon",
            modifier = Modifier
                .size(23.dp)
                .constrainAs(fileIcon) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                })

        Text(text = item.taskFiles.size.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            fontSize = 14.sp,
            modifier = Modifier
                .constrainAs(filesCount) {
                    start.linkTo(fileIcon.start)
                    bottom.linkTo(fileIcon.bottom, margin = -8.dp)
                })

        LinearProgressIndicator(
            progress = 0.5f,
            color = ProgressRed,
            trackColor = ProgressTrack,
            modifier = Modifier
                .background(shape = RoundedCornerShape(10.dp), color = TaskBackground)
                .constrainAs(progressBar) {
                    start.linkTo(fileIcon.start)
                    end.linkTo(fileIcon.end)
                    top.linkTo(fileIcon.bottom, margin = 3.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.value(2.dp)
                }
        )
    }

}

@Composable
fun CompletedTaskItem(item: Tasks) {

    val checkedState = remember { mutableStateOf(true) }

    val checkBoxColor = CheckboxDefaults.colors(
        checkedColor = ProgressChecked,
        uncheckedColor = PrimaryColor,
        checkmarkColor = PrimaryLightColor,
        disabledCheckedColor = ProgressChecked,
        disabledUncheckedColor = ProgressRed,
        disabledIndeterminateColor = ProgressRed,
    )




    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(shape = RoundedCornerShape(10.dp), color = TaskBackground)
            .padding(5.dp)
    ) {

        val (checkBoxRow, checkBox, taskText, fileIcon, filesCount, progressBar) = createRefs()

        Checkbox(checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            colors = checkBoxColor,
            modifier = Modifier
                .clickable { checkedState.value = !checkedState.value }
                .constrainAs(checkBox) {
                    centerVerticallyTo(parent)
                }
        )

        Text(item.taskName,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            color = ProgressChecked,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .constrainAs(taskText) {
                    start.linkTo(checkBox.end, margin = 0.dp, goneMargin = 0.dp)
                    end.linkTo(fileIcon.start, margin = 5.dp)
                    centerVerticallyTo(parent)
                })
        Image(painter = painterResource(id = R.drawable.file_icon),
            contentDescription = " file icon",
            colorFilter = ColorFilter.tint(ProgressChecked),
            modifier = Modifier
                .size(23.dp)
                .constrainAs(fileIcon) {
                    end.linkTo(parent.end)
                    centerVerticallyTo(parent)
                })
        Text(
            text = "2",
            fontWeight = FontWeight.Bold,
            color = ProgressChecked,
            fontSize = 14.sp,
            modifier = Modifier.constrainAs(filesCount) {
                start.linkTo(fileIcon.start)
                bottom.linkTo(fileIcon.bottom, (-10).dp)
            },
        )


    }
}
